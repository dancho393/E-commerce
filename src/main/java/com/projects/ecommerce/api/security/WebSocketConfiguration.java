package com.projects.ecommerce.api.security;

import com.projects.ecommerce.model.User;
import com.projects.ecommerce.service.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authorization.AuthorizationEventPublisher;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.SpringAuthorizationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.messaging.access.intercept.AuthorizationChannelInterceptor;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Map;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    private final ApplicationContext context;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AntPathMatcher MATCHER = new AntPathMatcher();//can be replaced by bean



    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").setAllowedOriginPatterns("**").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue");//see later just in case
        registry.setApplicationDestinationPrefixes("/app");
    }
    private AuthorizationManager<Message<?>> makeMessageAuthorizationManager() {
        MessageMatcherDelegatingAuthorizationManager.Builder messages=
                new MessageMatcherDelegatingAuthorizationManager.Builder();
        messages
                .simpDestMatchers("/topic/user/**").authenticated()

                .anyMessage().permitAll();
        return messages.build();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        AuthorizationManager<Message<?>> authorizationManager =
                makeMessageAuthorizationManager();

        AuthorizationChannelInterceptor authInterceptor =
                new AuthorizationChannelInterceptor(authorizationManager);

        AuthorizationEventPublisher publisher =
                new SpringAuthorizationEventPublisher(context);

        authInterceptor.setAuthorizationEventPublisher(publisher);
        registration.interceptors(jwtAuthenticationFilter,authInterceptor,
                new RejectClientMessagesOnChannelsChannelInterceptor(),
                new DestinationLevelAuthorizationChannelInterceptor());
    }
    private class RejectClientMessagesOnChannelsChannelInterceptor implements ChannelInterceptor {
        private final String[] paths=new String[]{
            "/topic/user/*/address",
        };
        @Override
        public Message<?> preSend(Message<?> message, MessageChannel channel) {
            if(message.getHeaders().get("simpMessageType").equals(SimpMessageType.MESSAGE)){
                String destination=(String)message.getHeaders()
                        .get("simpDestination");
                for(String path: paths){
                    if(MATCHER.match(path,destination)){
                        message = null;
                    }
                }
            }
            return message;
        }
    }
    private class DestinationLevelAuthorizationChannelInterceptor implements ChannelInterceptor {
        @Override
        public Message<?> preSend(Message<?> message, MessageChannel channel) {
            if(message.getHeaders().get("simpMessageType").equals(SimpMessageType.SUBSCRIBE)){
                String destination=(String)message.getHeaders()
                        .get("simpDestination");
                Map<String,String> params=MATCHER.extractUriTemplateVariables(
                        "/topic/user/{userId}/**",destination
                );
                try{
                    Long userId=Long.parseLong(params.get("userId"));
                    Authentication authentication =
                            SecurityContextHolder.getContext().getAuthentication();
                    if(authentication != null){
                        User user = (User)authentication.getPrincipal();
                    }
                }catch (NumberFormatException exception){
                    message = null;
                }
            }
            return message;
        }
    }
}

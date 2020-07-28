package info.ad80.springboot.backend.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	
	//CONFIGURAR ENDPOINT DE CONEXION
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat-websocket").setAllowedOrigins("http://localhost:4200")
		.withSockJS();
		
	}

	//CONFIGURACION DE EVENTOS DE CHAT
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
	registry.enableSimpleBroker("/chat/");  // con esto le indicamos a los usuarios en pantalla que se ha producido un evento de chat 
	registry.setApplicationDestinationPrefixes("/app"); //este es el destino del payload (payload= mensaje q estamos enviando)
	}

	

		
	}



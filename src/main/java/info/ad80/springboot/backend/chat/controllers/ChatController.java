package info.ad80.springboot.backend.chat.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import info.ad80.springboot.backend.chat.models.documents.Mensaje;

@Controller
public class ChatController {
	
	private String[] colores = {"red", "green","blue", "magenta", "purple", "orange"};
	
	@MessageMapping("/mensaje") // El cliente cada vez que escribe algo lo envía a /app/mensaje, porque configuramos el prefijo api en la clase WebSocketConfig
	@SendTo("/chat/mensaje")  //Esta es la notificación a todos los clientes suscritos al evento de nombre "chat", que es el prefijo que dimos en clase WebSocketConf
	private Mensaje recibeMensaje(Mensaje mensaje) { 
		mensaje.setFecha(new Date().getTime());
		
		if (mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("nuevo usuario");
			
			
		}
		
		mensaje.setTexto("Recibido por el broker: " + mensaje.getTexto());
			return mensaje;
	}
	
	
	@MessageMapping("/escribiendo") // El cliente cada vez que escribe algo lo envía a /app/mensaje, porque configuramos el prefijo api en la clase WebSocketConfig
	@SendTo("/chat/escribiendo") 
	//Metodo para verificar si un usuario esta escribiendo
	public String estaEscribiendo(String username) {
		return username.concat("está escribiendo...");   
	}
	
}

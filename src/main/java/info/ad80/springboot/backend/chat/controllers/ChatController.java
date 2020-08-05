package info.ad80.springboot.backend.chat.controllers;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import info.ad80.springboot.backend.chat.models.documents.Mensaje;

@Controller
public class ChatController {
	
	@MessageMapping("/mensaje") // El cliente cada vez que escribe algo lo envía a /api/mensaje, porque configuramos el prefijo api en la clase WebSocketConfig
	@SendTo("/chat/mensaje")  //Esta es la notificación a todos los clientes suscritos al evento de nombre "chat", que es el prefijo que dimos en clase WebSocketConf
	private Mensaje recibeMensaje(Mensaje mensaje) { 
		mensaje.setFecha(new Date().getTime());
		mensaje.setTexto("Recibido por el broker: " + mensaje.getTexto());
			return mensaje;
	}


@MessageMapping("/escribiendo") // El cliente cada vez que escribe algo lo envía a /api/mensaje, porque configuramos el prefijo api en la clase WebSocketConfig
@SendTo("/chat/escribiendo")
public String estaEscribiendo(String username) {
	return username.concat(" está escribiendo...");
}

}

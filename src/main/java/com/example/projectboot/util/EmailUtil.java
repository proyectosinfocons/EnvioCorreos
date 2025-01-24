package com.example.projectboot.util;

import com.example.projectboot.service.TicketSummaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Value("${email.username}")
	private String username;

	@Autowired
	private TicketSummaryService ticketSummaryService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

	public void sendEmailWithAttachment(String to, String subject, String text) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		// Establecer el remitente del correo
		helper.setFrom(username);

		// Establecer los destinatarios múltiples si los hay
		String[] destinatarios = to.split(","); // Separa los destinatarios por comas
		helper.setTo(destinatarios);  // Enviar a múltiples destinatarios

		LocalDate startDate = LocalDate.now().minusMonths(1).withDayOfMonth(1);  // Primer día del mes anterior
		LocalDate endDate = LocalDate.now().minusMonths(1).withDayOfMonth(LocalDate.now().minusMonths(1).lengthOfMonth());  // Último día del mes anterior
		int cantidad = ticketSummaryService.getTotalTicketsAtendidos(startDate, endDate);
		// Llama al servicio para obtener el total de tickets atendidos

		// Establecer el asunto y el cuerpo del correo
		helper.setSubject(subject);
		helper.setText("La cantidad total de tickets es :"+String.valueOf(cantidad));
		LOGGER.info("SUCCESS CORRECT");

		// Enviar el correo
		emailSender.send(message);
	}


	@Scheduled(cron = "0 0 9 1 * ?")
	public void sendScheduledEmail() throws MessagingException {
		// Obtener el mes y el año del mes anterior en español (Perú)
		LocalDate previousMonthDate = LocalDate.now().minusMonths(1); // Obtener el mes anterior
		Locale locale = new Locale("es", "PE");  // Establecer idioma español (Perú)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", locale);
		String formattedDate = previousMonthDate.format(formatter); // Ejemplo: "diciembre 2024" si es en enero 2025
		// Hacer que la primera letra del mes sea mayúscula y el resto minúscula
		formattedDate = formattedDate.substring(0, 1).toUpperCase() + formattedDate.substring(1);
		String to = "peter.valdivia@inferencelabs9.com,carlos.asmat@inferencelabs9.com,cesar.hinojosa@inferencelabs9.com"; // Destinatarios
		String subject = "[Sole] Cantidad de tickets atendidos por el DVA - " + formattedDate;
		String text = "Este es un correo enviado automáticamente el segundo día de cada mes.";

		// Llamar al método para enviar el correo
		sendEmailWithAttachment(to, subject, text);
	}
}

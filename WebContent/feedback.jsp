<div id="feedback_content" style="display: none;"><div class="show_payment"><div class="payment_content_sub"><div class="payment_content1">
	<div class="feedback_block">
		<h1>got a problem</h1>
		<p>For buying assistance or any other query, kindly get in touch with <a href="contacts.jsp">our support team</a>.</p>
	</div>
	<form action="#" method="post">
		<div class="feedback_block">
			<h1>Tell us what you think</h1>
			<p>Tell us how we can improve our website or share your own feedback with us</p>
			<input type="text" name="name" required='required' value="your name here" id="nameFeedback" onblur="javascript:if(this.value=='') this.value = 'your name here';" autocomplete="off" onfocus="javascript:if(this.value=='your name here') this.value = '';" />
			
			<input type="text" name="email" required='required' value="email address" id="emailFeedback" onblur="javascript:if(this.value=='') this.value = 'email address';" autocomplete="off" onfocus="javascript:if(this.value=='email address') this.value = '';" />
			
			<input type="text" name="contact" required='required' value="contact number" id="contact" onblur="javascript:if(this.value=='') this.value = 'contact number';" autocomplete="off" onfocus="javascript:if(this.value=='contact number') this.value = '';" />
			
			<input type="text" name="subject" required='required' value="subject" id="subject" onblur="javascript:if(this.value=='') this.value = 'subject';" autocomplete="off" onfocus="javascript:if(this.value=='subject') this.value = '';" />
			
			<textarea id="message" rows="" cols="" required='required' name="message" onblur="javascript:if(this.value=='') this.value = 'message';" autocomplete="off" onfocus="javascript:if(this.value=='message') this.value = '';">message</textarea>
			
			<input type="button" id="submit" value="send now" onclick="customerFeedback()" name="submit" />
		</div>
	</form>
<a class="popup-close" href="javascript:void():" id="feedback_cancel">&nbsp;</a>
</div></div></div><div class="payment_overlay">&nbsp;</div></div>
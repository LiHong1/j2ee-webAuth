
(function($){
	 var initParams={
				usernameId:'username',
				passwordId:'password',
				buttonId:'button',
				formId:'form',
				usernameValue:'',
				passwordValue:'',
				url:'',
				modulus:'',
				exponent:'',

	};
	$.fn.InitRegister=function(options){
		 var params=$.extend(initParams,options);
		    init(params);
		$(params.buttonId).click(function(){
 

			
		       //var password=CryptoJS.MD5($(params.passwordId).val());
		       var password=$(params.passwordId).val();
		       var random=Math.seedrandom();
		       var userInformation=BASE64.encoder($(params.usernameId).val())+":"+password+":"+BASE64.encoder(random.substring(0,30));
		     
		       alert(userInformation);
		       $.getJSON(params.url,function(data) {
		                   params.modulus = data[0].publicKey.modulus;
		                   params.exponent = data[0].publicKey.exponent;
		                   alert(params.modulus);
		                   alert(params.exponent);
		                  
		                   if (userInformation.length != 256) {
		                	   var publicKey = RSAUtils.getKeyPair(params.exponent, '', params.modulus);           
		                       $(params.passwordId).val(RSAUtils.encryptedString(publicKey, userInformation));
		                        $(params.usernameId).val();
		                       $(params.formId).submit();
		                   }  
		       });
        
		});
	};
	
	$.fn.InitResetPassword=function(options){
		alert("ertre");
		 var params=$.extend(initParams,options);
		    init(params);
		$(params.buttonId).click(function(){
			var oldPassword=$("#"+params.oldPasswordId);
			var newPassword1=$("#"+params.newPasswordId_1);
			var newPassword2=$("#"+params.newPasswordId_2);
			var random=Math.seedrandom();
			
			var information=oldPassword.val()+":"+newPassword1.val()+":"+newPassword2.val()+":"+BASE64.encoder(random.substring(0,30));
		       alert(information);
		       $.getJSON(params.url,function(data) {
		                   params.modulus = data[0].publicKey.modulus;
		                   params.exponent = data[0].publicKey.exponent;
		                   alert(params.modulus);
		                   alert(params.exponent);
		                  
		                   if (information.length != 256) {
		                	   var publicKey = RSAUtils.getKeyPair(params.exponent, '', params.modulus);           
		                	   oldPassword.val(RSAUtils.encryptedString(publicKey, information));
		                       newPassword2.val('*************');
		                       newPassword1.val('*************');
		                       $(params.formId).submit();
		                   }  
		       });
       
		});
	};
	$.fn.InitLogin=function(options){
		   var R1,mi,MK,R2;
		   var params=$.extend(initParams,options);
		    init(params);
		$(params.buttonId).click(function(){
			 $.getJSON(params.url,function(data) {
				 params.modulus = data[0].publicKey.modulus;
  	             params.exponent = data[0].publicKey.exponent;
  	                var param="";
  	                if(params.hasOwnProperty("param_0"))
  	                	param+="&"+$("#"+params.param_0).attr("name")+"="+$("#"+params.param_0).val();
  	                if(params.hasOwnProperty("param_1"))
  	                	param+="&"+$("#"+params.param_1).attr("name")+"="+$("#"+params.param_1).val();
  	                if(params.hasOwnProperty("param_2"))
  	                	param+="&"+$("#"+params.param_2).attr("name")+"="+$("#"+params.param_2).val();
  	                if(params.hasOwnProperty("param_3"))
  	                	param+="&"+$("#"+params.param_3).attr("name")+"="+$("#"+params.param_3).val();
  	                if(params.hasOwnProperty("param_4"))
  	                	param+="&"+$("#"+params.param_4).attr("name")+"="+$("#"+params.param_4).val();
  	                if(params.hasOwnProperty("param_5"))
  	                	param+="&"+$("#"+params.param_5).attr("name")+"="+$("#"+params.param_5).val();
  	                
  	                //alert(params.hasOwnProperty("param_0"));
  	                //alert(params.hasOwnProperty("param_1"));
  	             	//var param_0=$("#"+params.param_0).attr("name")+"="+$("#"+params.param_0).val();
  	      
				$.getJSON(params.url+"?"+$(params.usernameId).attr("name")+"="+ $(params.usernameId).val()+param,function(data) {
			    	           R1 = data[0].R1, mi = data[0].mi;
			    	           params.password=CryptoJS.MD5($(params.passwordId).val());
			    	           params.password=$(params.passwordId).val();

			                   MK=Encrypt(params.password,R1);

			                   R2=Decrypt(mi,MK.substring(0,16));

			    	           var random=Math.seedrandom();
			     
			    	           var epwd = R2+BASE64.encoder(random.substring(0,30));

		                       if (epwd.length != 256) {
		                           var publicKey = RSAUtils.getKeyPair(params.exponent , '', params.modulus);
		                           var password=RSAUtils.encryptedString(publicKey, epwd);
		                           $(params.passwordId).val(password); 

		                       }
		                     
		                       $(params.formId).submit();
			       });
	       
	           });
			 
			 
		});
		 
	};
	
	 function Decrypt(content,KEY){
         var key = CryptoJS.enc.Utf8.parse(KEY); 
         var iv  = CryptoJS.enc.Utf8.parse('0102030405060708'); 
         var decrypted = CryptoJS.AES.decrypt(content, key, { iv: iv,mode:CryptoJS.mode.CBC});
         return CryptoJS.enc.Utf8.stringify(decrypted).toString();
    };
	  
	   function Encrypt(content,KEY){
         var key = CryptoJS.enc.Utf8.parse(KEY); 
         var iv  = CryptoJS.enc.Utf8.parse('0102030405060708'); 
         var srcs = CryptoJS.enc.Utf8.parse(content);
         var encrypted = CryptoJS.AES.encrypt(srcs, key, { iv: iv,mode:CryptoJS.mode.CBC});
         return encrypted.toString();
    };
	   function init(params){
		 
			params.usernameId='#'+params.usernameId;
			params.passwordId='#'+params.passwordId;
			params.formId='#'+params.formId;
			params.buttonId='#'+params.buttonId;
	   };

})(jQuery);
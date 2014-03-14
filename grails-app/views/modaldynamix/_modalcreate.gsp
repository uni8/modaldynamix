<g:render template="/modaldynamix/${attrs.formType }" model="[attrs:attrs]" />
  				
<g:javascript>

	//CloseModal Closes specific 
	function ${attrs.submitController}CloseModal() {
		//Close modal window and remove backdrop
		$('#ModalDynamix${attrs.id}').dialog().dialog('close');
  		$(".modal-backdrop").hide();
  		
  		//Call if ckeditor defined - will clone ckeditor original
   		<g:if test="${attrs.ckeditor }">
  			// CKEDITOR specific call
    		if(CKEDITOR.instances['${attrs.ckeditor }']){
				CKEDITOR.remove(CKEDITOR.instances['myCKEditor']); //Does the same as line below
				$.get('${createLink(controller:"${attrs.ckController }", action: "${attrs.ckAction}")}',function(data){
					$('#${attrs.divId}1').hide().append(data);
				});
   		}
   		</g:if>
   		<g:else>
   			// Do the default action of returning cloned information
   			$('#${attrs.divId}1').hide().append(myClone${attrs.divId});
   		</g:else>
   		
  		// If disablecheck not set to true - the trigger queryController which is by default modaldynamix
  		// This calls getAjaxCall which lists the domain provided and sends it back to your original view       
		<g:if test="${!attrs.disablecheck.equals('true') }">
			var domain="${attrs.domain }";
			var divId="${attrs.divId }";
			var returnController="${attrs.returnController }";
			$.get('${createLink(controller:"${attrs.queryController}", action: "${attrs.queryAction}")}?domain='+domain+'&divId='+divId+'&returnController='+returnController,function(data){
				$('#${attrs.divId}').hide().html(data).fadeIn('slow');
			});
		</g:if>	
	}
</g:javascript>
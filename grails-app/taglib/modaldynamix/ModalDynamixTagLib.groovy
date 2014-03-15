package modaldynamix

class ModalDynamixTagLib {
  
	/*
	 * This loads in customised bootstrap.css and default bootstrap.js
	 * If your site already has these then no need to run, otherwise: 
	 *  
	 * <r:layoutResources />
	 * <g:loadboostrap/>
	 * </head>
	 * 
	 *(notice the extra tag loadboostrap above)
	 *In your main file to run in overall site 
	 *OR just call this on a specific gsp page if you have specific use
	 */
	 
	def loadbootstrap= {
		out << g.render(contextPath: pluginContextPath,template: 'loadbootstrap')
	}
	
	
	def showTopScript= {attrs,body->
		
		if (!attrs.divId) {
			throwTagError("Tag [loadShowScript] is missing required attribute [divId]")
		}
		
		if (!attrs.scriptName) {
			throwTagError("Tag [loadShowScript] is missing required attribute [scriptName]")
		}
		
		out << """
			<script type="text/javascript">
 	 		function ${attrs.scriptName}() {
 	 	 		\$('#${attrs.divId}').show();
 	 		}
 	 	    </script>
           """
	}

	def modalForm={attrs,body ->
		
		// Set the form to load by default selfPosting form [OVERRIDABLE just set close="CLOSE" ]
		// SHOULD BE OVERRIDEN TO DEFINE FORM TYPE:
		// AVAILABLE:
		// modalSelfPostForm - self posting form default requires a complete form to be provided as modalTemplate param below
		// modalRemoteForm - A remote Form that requires more configuration such as form action controller +side: allows you to provide a fields only form.
		// modalbasic - just a basic pop up that the user does something in and it self does what ever you want - nothing besides site opening it
		// modaliframe - opens a modalbox with an iframe within it - a url is produced and sent to the iframe - you need to configure the url to display a form back 
		
		if (!attrs.formType) { 
			attrs.formType='modalSelfPostForm'
		}
		
		// Set the close button	[OVERRIDABLE just set close="CLOSE" ]
		if (!attrs.close) {
			attrs.close='X'
		}
		
		// Return error if nothing provided for remote i.e Template outside of plugin 
		// and within the path of your own project is not given i.e. /yourview_folder/form
		// This will interrogate your local project yourview_folder/ and load _form.gsp 
		// to show within the selfposting form
		// Self posting forms must be a complete form meaning the _form is not a good example
		// since this usually contains only the fields.
		// For a fields only scenario refer to modalRemoteForm FormType 
		if (!attrs.modalTemplate) {
			throwTagError("Tag [modalForm] is missing required attribute [modalTemplate]")
		}
		
		// Default submit response [OVERRIDABLE just set submitValue="something" ]
		if (!attrs.submitValue) { 
			attrs.submitValue="Submit"
		}
		
		// This is the default controller - lives within this plugin [OVERRIDABLE just set queryController="myQueryController" ]
		if (!attrs.queryController) {
			attrs.queryController="modaldynamix"
		}
		// This is the default Action for this field is getAjaxCall which resides in above default controller [OVERRIDABLE just set queryAction="myAction" ]
		if (!attrs.queryAction) { 
			attrs.queryAction="getAjaxCall"
		}
		
		
			
		out << g.render(contextPath: pluginContextPath,template: attrs.modalTemplate, model: [attrs:attrs])
		
	}
	

	
}

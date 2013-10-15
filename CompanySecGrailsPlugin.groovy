class CompanySecGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.1 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def loadAfter = ['springSecurityCore','springSecurityLdap']

    // TODO Fill in these fields
    def title = "Company Sec Plugin" // Headline display name of the plugin
    def author = "Your name"
    def authorEmail = ""
    def description = '''\
Brief summary/description of the plugin.
'''

    def doWithConfig = { config ->
        application {
            grails.plugins.springsecurity.ldap.active = true
            grails.plugins.springsecurity.ldap.authorities.retrieveDatabaseRoles = true
            grails.plugins.springsecurity.ldap.authorities.retrieveGroupRoles = true
            grails.plugins.springsecurity.ldap.context.server = 'ldap://<myip>'
            grails.plugins.springsecurity.ldap.context.managerDn = 'CN=<ldap_user>,CN=Users,DC=company,DC=local'
            grails.plugins.springsecurity.ldap.context.managerPassword = 'secret'
			
            grails.plugins.springsecurity.userLookup.userDomainClassName = 'com.mycompany.StdUser'
            grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'com.mycompany.StdUserStdRole'
            grails.plugins.springsecurity.authority.className = 'com.mycompany.StdRole'
            grails.plugins.springsecurity.useSecurityEventListener = true
            // more springsecurity settings...

            if (grails.plugins.springsecurity.ldap.active) {
                grails.plugins.springsecurity.providerNames = ['ldapAuthProvider', 'anonymousAuthenticationProvider']
            }
        }

    }
}

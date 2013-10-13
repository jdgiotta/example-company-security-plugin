package com.mycompany

import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent
import org.apache.juli.logging.LogFactory

class RegisterStdUser implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
    private static final log = LogFactory.getLog(this)
    def sessionFactory
    void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        log.debug "Successfully logged in..."
        StdUser.withTransaction {
            StdUser.findByUsername(event.source.principal?.getAt('username')) ?:
                new StdUser(username: event.source.principal?.getAt('username'),
                        password: 'KU)(&#U)(&(#UIHAHQD&*Y*(Y@(EHY*(QY(*@YH#*H',
                        enabled: true).save(failOnError: true)
        }
    }
}

package com.mycompany

import org.apache.commons.lang.builder.HashCodeBuilder

class StdUserStdRole implements Serializable {

    StdUser stdUser
    StdRole stdRole

    boolean equals(other) {
        if (!(other instanceof StdUserStdRole)) {
            return false
        }

        other.stdUser?.id == stdUser?.id &&
                other.stdRole?.id == stdRole?.id
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        if (stdUser) builder.append(stdUser.id)
        if (stdRole) builder.append(stdRole.id)
        builder.toHashCode()
    }

    static StdUserStdRole get(long stdUserId, long stdRoleId) {
        find 'from StdUserStdRole where stdUser.id=:stdUserId and stdRole.id=:stdRoleId',
                [stdUserId: stdUserId, stdRoleId: stdRoleId]
    }

    static StdUserStdRole create(StdUser stdUser, StdRole stdRole, boolean flush = false) {
        new StdUserStdRole(stdUser: stdUser, stdRole: stdRole).save(flush: flush, insert: true)
    }

    static boolean remove(StdUser stdUser, StdRole stdRole, boolean flush = false) {
        StdUserStdRole instance = StdUserStdRole.findByStdUserAndStdRole(stdUser, stdRole)
        if (!instance) {
            return false
        }

        instance.delete(flush: flush)
        true
    }

    static void removeAll(StdUser stdUser) {
        executeUpdate 'DELETE FROM StdUserStdRole WHERE stdUser=:stdUser', [stdUser: stdUser]
    }

    static void removeAll(StdRole stdRole) {
        executeUpdate 'DELETE FROM StdUserStdRole WHERE stdRole=:stdRole', [stdRole: stdRole]
    }

    static mapping = {
        id composite: ['stdRole', 'stdUser']
        version false
    }
}

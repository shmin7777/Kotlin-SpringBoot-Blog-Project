package com.example.simpleblog.domain.member

import com.example.simpleblog.domain.AuditingEntity
import jakarta.persistence.*

/**
 * 유저
 */
@Entity
@Table(name = "Member")
class Member(
    email: String,
    password: String,
    role: Role
) : AuditingEntity() {

    /**
     * email
     */
    @Column(name = "email", nullable = false)
    var email: String = email
        protected set

    /**
     * password
     */
    @Column(name = "password")
    var password: String = password
        protected set

    /**
     * 권한
     */
    @Enumerated(EnumType.STRING)
    var role: Role = role
        protected set

    override fun toString(): String {
        return "Member(email='$email', password='$password', role=$role)"
    }


}

enum class Role {
    USER, ADMIN
}
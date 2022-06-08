package com.example.hospital.model


import javax.persistence.*


@Entity
@Table(name = "employee",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["email"])
    ])
class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var name: String,
    var surname: String,
    var patronymic: String,
    var phoneNumber: String,
    var email: String,
    var password: String,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_roles",
        joinColumns = [JoinColumn(name = "employee_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")])
    var roles: Set<Role>
)

package me.osmanfurkan.licenseapi.model

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "clients")
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var discordAddress: String,

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val licenseList: MutableList<License> = mutableListOf(),

    @CreatedDate
    val createdDate: LocalDateTime = LocalDateTime.now()
)

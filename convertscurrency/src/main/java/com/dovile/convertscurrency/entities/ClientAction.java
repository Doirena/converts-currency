package com.dovile.convertscurrency.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Dovile Barkauskaite <barkauskaite.dovile@gmail.com>
 */
@Entity
@Table(name = "client_action")
@NamedQueries({
        @NamedQuery(name = "ClientAction.findAll", query = "SELECT c FROM ClientAction c"),
        @NamedQuery(name = "ClientAction.findById", query = "SELECT c FROM ClientAction c WHERE c.id = :id"),
        @NamedQuery(name = "ClientAction.findByAction", query = "SELECT c FROM ClientAction c WHERE c.action = :action"),
        @NamedQuery(name = "ClientAction.findByCreatedAt", query = "SELECT c FROM ClientAction c WHERE c.createdAt = :createdAt")})
public class ClientAction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "action")
    private String action;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    public ClientAction() {
    }

    public ClientAction(Integer id) {
        this.id = id;
    }

    public ClientAction(Integer id, String action) {
        this.id = id;
        this.action = action;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientAction)) {
            return false;
        }
        ClientAction other = (ClientAction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  id + " "+action;
    }

}

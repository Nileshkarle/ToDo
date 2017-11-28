package com.bridgelab.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SHARED_NOTES")
public class Collaborator {

	@Id
	@GenericGenerator(name = "abc", strategy = "increment")
	@GeneratedValue(generator = "abc")
	private int collaboratorId;
	
	@ManyToOne
	@JoinColumn(name="ownerId")
	private User ownerId;
	
	@ManyToOne
	@JoinColumn(name="shareWithId")
	private User shareWithId;
	
	@ManyToOne
	@JoinColumn(name="noteId")
	private Notes note;

	public int getCollaboratorId() {
		return collaboratorId;
	}

	public User getOwnerId() {
		return ownerId;
	}

	public User getShareWithId() {
		return shareWithId;
	}

	public Notes getNote() {
		return note;
	}

	public void setCollaboratorId(int collaboratorId) {
		this.collaboratorId = collaboratorId;
	}

	public void setOwnerId(User ownerId) {
		this.ownerId = ownerId;
	}

	public void setShareWithId(User shareWithId) {
		this.shareWithId = shareWithId;
	}

	public void setNote(Notes note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Collaborator [collaboratorId=" + collaboratorId + ", ownerId=" + ownerId + ", shareWithId="
				+ shareWithId + ", note=" + note + "]";
	}
}

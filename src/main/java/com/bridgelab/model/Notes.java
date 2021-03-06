package com.bridgelab.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TODO_NOTES")
public class Notes {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "notegen")
	@GenericGenerator(name = "notegen", strategy = "native")
	private int id;

	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "TITLE")
	private String title;

	@Column(name = "PIN")
	private String pin;
	
	@Column(name="ARCHIVE_STATUS")
	private String archiveStatus;
	
	@Column(name="DELETE_STATUS")
	private String deleteStatus;
	
	@Column(name="REMINDER_STATUS")
	private String reminderStatus;
	
	@Column(name = "NOTE_STATUS")
	private String noteStatus;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name="NOTE_COLOR")
	private String noteColor;
	
	@Lob
	@Column(name="IMAGE",columnDefinition="LONGBLOB")
	private String image;
	
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Label> labels;
	
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name = "USER_ID")
	private User user;

	public Set<Label> getLabels() {
		return labels;
	}

	public void setLabels(Set<Label> labels) {
		this.labels = labels;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNoteColor() {
		return noteColor;
	}

	public void setNoteColor(String noteColor) {
		this.noteColor = noteColor;
	}

	public String getNoteStatus() {
		return noteStatus;
	}

	public void setNoteStatus(String noteStatus) {

		if(noteStatus.equals("true") || noteStatus.equals("false") ) {
			this.noteStatus = noteStatus;
		}
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		if (pin.equals("true") || pin.equals("false")) {
			this.pin = pin;
		}
	}
	
	public String getArchiveStatus() {
		return archiveStatus;
	}

	public void setArchiveStatus(String archiveStatus) {
		if (archiveStatus.equals("true") || archiveStatus.equals("false")) {
		this.archiveStatus = archiveStatus;
		}
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		if (deleteStatus.equals("true") || deleteStatus.equals("false")) {
		this.deleteStatus = deleteStatus;
		}
	}

	public String getReminderStatus() {
		return reminderStatus;
	}

	public void setReminderStatus(String reminderStatus) {	
		this.reminderStatus = reminderStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Notes [id=" + id + ", description=" + description + ", title=" + title + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", user=" + user + "]";
	}

}

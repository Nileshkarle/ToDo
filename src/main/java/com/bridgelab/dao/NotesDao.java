package com.bridgelab.dao;

import java.util.List;

import com.bridgelab.model.Notes;
import com.bridgelab.model.User;

/**
 * @author Nilesh
 *
 * @Description All notes related database operation are done in NoteDao.
 * 
 */
public interface NotesDao {

	public void addUserNotes(Notes notes);
	
	public void deleteNote(Notes notes);
	
	public void updateNote(Notes notes);
	
	public Notes getNote(Notes note);
	
	public List<Notes> listAllNotes(User userId);
	
}

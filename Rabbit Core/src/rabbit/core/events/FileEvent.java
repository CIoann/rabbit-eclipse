/*
 * Copyright 2010 The Rabbit Eclipse Plug-in Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rabbit.core.events;

import java.util.Calendar;

import org.eclipse.mylyn.tasks.core.ITask;

/**
 * Represents a file event. This object stores the file id instead of the file
 * itself.
 * 
 * @see rabbit.core.RabbitCore#getResourceManager()
 */
public class FileEvent extends ContinuousEvent {

	private String fileId;
	private ITask task;

	/**
	 * Constructs a new event.
	 * 
	 * @param time
	 *            The end time of the event.
	 * @param duration
	 *            The duration of the event, in milliseconds.
	 * @param fileId
	 *            The id of the file.
	 * @throws NullPointerException
	 *             If time is null or file id is null.
	 * @throws IllegalArgumentException
	 *             If duration is negative.
	 */
	public FileEvent(Calendar time, long duration, String fileId) {
		super(time, duration);
		setFileId(fileId);
		setTask(null);
	}

	/**
	 * Gets the file id.
	 * 
	 * @return The file id.
	 * @see rabbit.core.RabbitCore#getResourceManager()
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * Gets the active task of this event.
	 * 
	 * @return The active task of this event, or null if none.
	 * @since 1.1
	 */
	public ITask getTask() {
		return task;
	}

	/**
	 * Sets the file id.
	 * 
	 * @param fileId
	 *            The file id.
	 * @throws NullPointerException
	 *             If argument is null.
	 * @see rabbit.core.RabbitCore#getResourceManager()
	 */
	public void setFileId(String fileId) {
		if (fileId == null) {
			throw new NullPointerException("Argument cannot be null");
		}
		this.fileId = fileId;
	}

	/**
	 * Sets the active task of this event.
	 * 
	 * @param task
	 *            The task, or null if none.
	 * @since 1.1
	 */
	public void setTask(ITask task) {
		this.task = task;
	}

}

package com.sinontech.service;

import com.sinontech.modle.Webchat;

public interface WebchatService {

	public void updateWebchat(Webchat webchat);
	public Webchat getWebchatById(long id);
	public Webchat selectFindByChannelId(long user_id);
}

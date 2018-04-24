package com.sinontech.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.ExtensionContentDaoImpl;
import com.sinontech.dao.impl.ExtensionUserDaoImpl;
import com.sinontech.modle.ExtensionContent;
import com.sinontech.modle.ExtensionUser;
import com.sinontech.modle.Rechargelog;
import com.sinontech.modle.Rechargeset;
import com.sinontech.modle.UserInfo;
import com.sinontech.service.ExtensionContentService;

@Service("extensioncontentservice")
public class ExtensionContentServiceImpl implements ExtensionContentService {
	@Autowired
	ExtensionContentDaoImpl extensionContentDaoImpl;
	@Autowired
	ExtensionUserDaoImpl extensionUserDaoImpl;
	
	
	@Override
	public ExtensionContent selectFindById(long EXTENSION_CONTENT_ID) {
		return this.extensionContentDaoImpl.selectFindById(EXTENSION_CONTENT_ID);
	}

	@Override
	public void updateExtensionContent(ExtensionContent extensionContent) {
		this.extensionContentDaoImpl.updateExtensionContent(extensionContent);
	}

	@Override
	public void updateExtensionContent(UserInfo user, Rechargelog log) {
		//推广链接增加充值金额
		long recharges = log.getMoney();
		long tid1 = user.getSpreadSource();
		long tid2 = user.getSpreadSource1();
		if(tid1>0){
			ExtensionContent extensionContent = this.extensionContentDaoImpl.selectFindById(tid1);
			ExtensionUser extensionUser = this.extensionUserDaoImpl.getExtensionUserByTIdandUid(tid1, user.getId());
			if(null!=extensionUser){
				int times = extensionUser.getRechargeTimes();
				if(times==0){
					if(null!=extensionContent){
						extensionContent.setRechargeTimes(extensionContent.getRechargeTimes()+1);
					}
				}
				extensionUser.setRechargeTimes(extensionUser.getRechargeTimes()+1);
				extensionUser.setRecharge(extensionUser.getRecharge()+recharges);
				this.extensionUserDaoImpl.updateExtensionUser(extensionUser);
			}
			if(null!=extensionContent){
				extensionContent.setRecharge(extensionContent.getRecharge() + recharges);
				this.extensionContentDaoImpl.updateExtensionContent(extensionContent);
			}
		}
		if(tid2>0){
			ExtensionContent extensionContent = this.extensionContentDaoImpl.selectFindById(tid2);
			ExtensionUser extensionUser = this.extensionUserDaoImpl.getExtensionUserByTIdandUid(tid2, user.getId());
			if(null!=extensionUser){
				int times = extensionUser.getRechargeTimes();
				if(times==0){
					if(null!=extensionContent){
						extensionContent.setRechargeTimes(extensionContent.getRechargeTimes()+1);
					}
				}
				extensionUser.setRechargeTimes(extensionUser.getRechargeTimes()+1);
				extensionUser.setRecharge(extensionUser.getRecharge()+recharges);
				this.extensionUserDaoImpl.updateExtensionUser(extensionUser);
			}
			if(null!=extensionContent){
				extensionContent.setRecharge(extensionContent.getRecharge() + recharges);
				this.extensionContentDaoImpl.updateExtensionContent(extensionContent);
			}
		}
	}
	
}

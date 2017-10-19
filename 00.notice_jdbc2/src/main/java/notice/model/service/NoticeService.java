package notice.model.service;

import java.util.ArrayList;

import notice.model.dao.NoticeDao;
import notice.model.dto.NoticeDto;

public class NoticeService {
	
	private NoticeDao noticeDao;
	
	
	public NoticeService() {
		
	}
	public NoticeService(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	public ArrayList<NoticeDto> showList(){
		return noticeDao.noticeList();
	}
}

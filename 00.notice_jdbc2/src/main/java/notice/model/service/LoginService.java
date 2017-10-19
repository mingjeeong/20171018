package notice.model.service;

import notice.model.dao.LoginDao;
import notice.model.dao.NoticeDao;

public class LoginService {
	
	private LoginDao loginDao;
	
   public LoginService() {
	   
   }
   
   public  LoginService(LoginDao loginDao) {
	   this.loginDao = loginDao;
   }
	
	public boolean login(String id,String pw) {
		return loginDao.loginCheck(id, pw);
	}
}

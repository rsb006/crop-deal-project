package com.cg.cropdeal.user.service; 

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cg.cropdeal.user.model.Address;
import com.cg.cropdeal.user.model.Bank;
import com.cg.cropdeal.user.model.User;
import com.cg.cropdeal.user.repository.UserRepository;

@Service
public class UserService implements IUserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public String AddUser(User user) {
			
	       userRepository.save(user);
	       return "user Added";
		
	}

	@Override
	public String deleteUser(Long userId) {
		
		/*
		 * fetch user from db
		 * check if user is not null
		 *     - if not null then delete the user 
		 *           cross check by fetching same user from db and comparing with null
		 *           return msg "user deleted successfully"
		 *     - else return msg "invalid id user not present"
		 */
		User user=userRepository.getByUserId(userId);
		if(user!=null) {
			userRepository.deleteById(userId);
			if(userRepository.getByUserId(userId)==null)
				return "user deleted successfully";
			
		}
		return "invalid id user not present";
	}

	@Override
	public User GetUser(Long userId) {
		
		/*
		 * if user data is present in database then return user
		 * 
		 * For this fetch user using the given id
		 * check if user is not null 
		 *   -if not null then return user data
		 * 
		 */
		
		User user=userRepository.getByUserId(userId);
		if(user!=null) {
			return user;
		}
		
		return null;
	}

	@Override
	public User updateUser(Long userId, User user) {
		
		//fetch user from database using given id
        User user1=userRepository.getByUserId(userId);
		
        /*
         
          check if requested  user data is not null
          check all the fields with null value and update that user fetched from database 
          with given details that are not null
           
       */
        
		if(user!=null) {
		if(user.getEmailId()!=null ) {
			user1.setEmailId(user.getEmailId());
		}
		if((user.getMobileNo()!=0)) {
			user1.setMobileNo(user.getMobileNo());
		}
		if(user.getPassword()!=null ) {
			user1.setPassword(user.getPassword());
		}
		if(user.getUserFullName()!=null ) {
			user1.setUserFullName(user.getUserFullName());
		}
		if(user.getUserType()!=null ) {
			user1.setUserType(user.getUserType());
		}
		
		
		Bank bank1=new Bank();
	    if(user.getBank()!=null) {
		
		if(user.getBank().getAccountHolderName()!=null ) {
		bank1.setAccountHolderName(user.getBank().getAccountHolderName());
		}
		
		if(user.getBank().getAccountNo()!=null ) {
		bank1.setAccountNo(user.getBank().getAccountNo());
		}
		
		if(user.getBank().getBankBranch()!=null ) {
		bank1.setBankBranch(user.getBank().getBankBranch());
		}
		
		
         if(user.getBank().getBankIFSC()!=null ) {
        	 bank1.setBankIFSC(user.getBank().getBankIFSC());
		}

         if(user.getBank().getBankName()!=null ) {
    	   bank1.setBankName(user.getBank().getBankName());
         }
         
         
	    }
	    else {
		bank1=user1.getBank();
	    }
		
         Address address1 =new Address();
		
         
         if(user.getAddress()!=null) {
         
		    if(user.getAddress().getCity()!=null ) {
		    	address1.setCity(user.getAddress().getCity());
		    }
        
		    if(user.getAddress().getCountry()!=null ) {
		    	address1.setCountry(user.getAddress().getCountry());
		    }
 
          
		    if(user.getAddress().getHouseNo()!=null ) {
		    	address1.setHouseNo(user.getAddress().getHouseNo());
		    }
		

		    if(user.getAddress().getLocalityName()!=null ) {
		    	address1.setLocalityName(user.getAddress().getLocalityName());
		    }
        
		    if(user.getAddress().getPincode()!=0 ) {
		    	address1.setPincode(user.getAddress().getPincode());
		    }

		
		    if(user.getAddress().getState()!=null ) {
		    	address1.setState(user.getAddress().getState());
        
		    }
        
		    if(user.getAddress().getStreetName()!=null ) {
		    	address1.setStreetName(user.getAddress().getStreetName());
		    }
		
         }
         
         	else {
        	    address1=user1.getAddress();
         	}
		
           user1.setBank(bank1);
           user1.setAddress(address1);
		
		    return userRepository.save(user1);
	 }
		
	
	  return user1;
	
	}
	
	
	
	

}

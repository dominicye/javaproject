package com.avnet.alapps.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.avnet.alapps.model.gsfc.Users;
import com.avnet.alapps.model.gsfc.Operation;;

public class SecurityUser extends Users implements UserDetails {
	private static final long serialVersionUID = 1L;

    private ArrayList<Operation> customerizedOperations;
    private ArrayList<GrantedAuthority> authorities;
    private String fullName;
    private String genesisId;
    
    public SecurityUser(Users u) {
    			super.setUserId(u.getUserId());
    			super.setContact(u.getContact());
    			super.setUserRole(u.getUserRole());
    			super.setActiveFl(u.getActiveFl());
    			super.setAvnetGlobalUserId(u.getAvnetGlobalUserId());
    			super.setCreateDt(u.getCreateDt());
    			super.setCreateUserId(u.getCreateUserId());
    			super.setInternalUserFl(u.getInternalUserFl());
    			super.setLastLoginDt(u.getLastLoginDt());
    			super.setLoginNm(u.getLoginNm());
    			super.setPasswordTx(u.getPasswordTx());
    			super.setUpdateDt(u.getUpdateDt());
    			super.setUpdateUserId(u.getUpdateUserId());
    			super.setSelfInspectionQt(u.getSelfInspectionQt());
    			super.setUserOperations(u.getUserOperations());    			
    			//dont need these, so dont make hibernate lazy load them
    			//u.getUserses()
    			//u.getUserContacts()
    			//u.getUsers()		
    }

    public String getFullName() {
    	if (fullName == null && this.getContact() != null  ) {
    			fullName = this.getContact().getFirstNm() + " " + this.getContact().getLastNm();
    	}
		return fullName;
	}
    
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setCustomerizedOperations(ArrayList<Operation> customerizedOperations) {
		this.customerizedOperations = customerizedOperations;
	}

	public List<Operation> getCustomerizedOperations() {
		return customerizedOperations;
	}

	
	public void setAuthorities(ArrayList<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	
	public String getGenesisId() {
		return genesisId;
	}

	public void setGenesisId(String genesisId) {
		this.genesisId = genesisId;
	}

	@Override
	public ArrayList<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return super.getPasswordTx();
	}
	@Override
	public String getUsername() {
		return super.getAvnetGlobalUserId();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}

	
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
	
}

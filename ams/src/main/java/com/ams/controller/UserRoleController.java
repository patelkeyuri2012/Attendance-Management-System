package com.ams.controller;

import com.ams.entity.Role;
import com.ams.entity.User;
import com.ams.service.RoleService;
import com.ams.service.UserRoleService;
import com.ams.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserRoleController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;

	@PostMapping("/editUserRoles")
	public String editUserRoles(@RequestParam("userroleid") Integer userroleid,
	                            @RequestParam("userid") Integer userId,
	                            @RequestParam("roleid") Integer roleId, Model model) {
	    
		User user = userService.getUserByUserid(userId);
	    Role role = roleService.getRoleByRoleid(roleId);

	    userRoleService.update(userroleid, user, role);
	    return "redirect:/admin/role.html";
	}
	
	@PostMapping("/blockUser")
    public String blockUser(@RequestParam("userroleid") Integer userRoleId) {
        userService.blockUser(userRoleId);
        return "redirect:/admin/role.html";
    }
	
	@PostMapping("/unblockUser")
    public String unblockUser(@RequestParam("userroleid") Integer userRoleId) {
        userService.unblockUser(userRoleId);
        return "redirect:/admin/role.html";
    }

}

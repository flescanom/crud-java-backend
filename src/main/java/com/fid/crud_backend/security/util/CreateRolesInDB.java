//package com.fid.crud_backend.security.util;
//
//import com.fid.crud_backend.security.service.RoleService;
//import com.fid.crud_backend.security.entity.Role;
//import com.fid.crud_backend.security.enums.RoleList;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// *
// * @author flesc
// */
//public class CreateRolesInDB {
//
//    @Autowired
//    RoleService roleService;
//
//    public void run(String... args) throws Exception {
//
//        Role roleAdmin = new Role(RoleList.ROLE_ADMIN);
//        Role roleUser = new Role(RoleList.ROLE_USER);
//        roleService.save(roleAdmin);
//        roleService.save(roleUser);
//
//    }
//}

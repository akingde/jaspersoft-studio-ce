/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jaspersoft.ireport.jasperserver.ws.examples;

import com.jaspersoft.ireport.jasperserver.ws.JServer;
import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.ireport.jasperserver.ws.WSRole;
import com.jaspersoft.ireport.jasperserver.ws.WSUser;
import com.jaspersoft.ireport.jasperserver.ws.permissions.WSObjectPermission;
import com.jaspersoft.ireport.jasperserver.ws.userandroles.WSRoleSearchCriteria;

/**
 *
 * @author gtoffoli
 */
public class CreateRoleAndUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        JServer server = new JServer();

        server.setUrl("http://127.0.0.1:8080/jasperserver-pro/services/repository");
        server.setUsername("jasperadmin|organization_1");
        server.setPassword("jasperadmin");

        WSClient client = new WSClient(server);

        createRole(client, "ROLE_ACCESS_MY_FOLDER");
        createUser(client, "myuser", "My Full User Name", "mypassword", "ROLE_ACCESS_MY_FOLDER");

    }

    public static void createRole(WSClient client, String roleName) throws Exception
    {

        WSRole dirRole = new WSRole( roleName,Boolean.FALSE, "organization_1", new WSUser[]{});

        // You can remove an existing role in this way:
        //client.getUserAndRoleManagementService().deleteRole(dirRole);


        client.getUserAndRoleManagementService().putRole(dirRole);

        System.out.println("Role " +  roleName + " created.");



        // Set permissions for that role...
        System.out.println("putting permission for: " + "/MY_FOLDER");

        // Adding Read and Execution permissions to this ROLE for the folder /MY_FOLDER
        client.getPermissionsManagement().putPermission(new WSObjectPermission("repo:/MY_FOLDER", dirRole, 2));

    }


    private static void createUser(WSClient client, String username, String fullName, String password, String roleName) throws Exception {

        
        // get the user role and the dir role we just created...
        //WSRole userRole = new WSRole("ROLE_USER",Boolean.FALSE,null, null);
        WSRole[] roles = client.getUserAndRoleManagementService().findRoles(new WSRoleSearchCriteria(roleName, "organization_1",Boolean.TRUE,Integer.MAX_VALUE));

        WSUser newUser = new WSUser(username, fullName, password, null, Boolean.FALSE, Boolean.TRUE, null, "organization_1" , roles);

        // Use this method to delete a user
        client.getUserAndRoleManagementService().deleteUser(newUser);
		// new WSUserSearchCriteria(name, tenantId, includeSubOrgs, requiredRoles,
		// maxRecords);
        client.getUserAndRoleManagementService().putUser(newUser);

        

        System.out.println("User " +  username + " created.");

    }
}

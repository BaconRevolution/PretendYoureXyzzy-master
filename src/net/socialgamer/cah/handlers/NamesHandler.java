/**
 * Copyright (c) 2012, Andy Janata
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 * 
 * * Redistributions of source code must retain the above copyright notice, this list of conditions
 *   and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice, this list of
 *   conditions and the following disclaimer in the documentation and/or other materials provided
 *   with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.socialgamer.cah.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.socialgamer.cah.Constants.AjaxOperation;
import net.socialgamer.cah.Constants.AjaxResponse;
import net.socialgamer.cah.Constants.ReturnableData;
import net.socialgamer.cah.RequestWrapper;
import net.socialgamer.cah.data.ConnectedUsers;
import net.socialgamer.cah.data.User;

import com.google.inject.Inject;


/**
 * Handler to get the names of all players connected to the server.
 * 
 * @author Andy Janata (ajanata@socialgamer.net)
 */
public class NamesHandler extends Handler {

  public static final String OP = AjaxOperation.NAMES.toString();

  private final ConnectedUsers users;

  @Inject
  public NamesHandler(final ConnectedUsers users) {
    this.users = users;
  }

  @Override
  public Map<ReturnableData, Object> handle(final RequestWrapper request,
      final HttpSession session) {
    final Map<ReturnableData, Object> ret = new HashMap<ReturnableData, Object>();
    // TODO once there are multiple rooms, we need which one was asked for
    final Collection<User> userList = users.getUsers();
    final List<String> names = new ArrayList<String>(userList.size());
    for (final User u : userList) {
      names.add(u.getNickname());
    }
    ret.put(AjaxResponse.NAMES, names);
    return ret;
  }
}

/**
 * Contains classes to access data according to the <a href="http://jsonapi.org/">JSON-API specification</a>.
 */
// everybody can read from the api
@ReadPermission(any={Role.ALL.class })
// By default restrict all data manipulation operation
// @UpdatePermission(expression = "is logged in")
@UpdatePermission(any={Role.NONE.class })
@CreatePermission(any={Role.NONE.class })
@DeletePermission(any={Role.NONE.class })
@SharePermission(any={Role.NONE.class })
package com.faforever.api.data;

import com.yahoo.elide.annotation.*;
import com.yahoo.elide.security.checks.prefab.Role;

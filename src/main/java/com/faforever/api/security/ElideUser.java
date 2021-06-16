package com.faforever.api.security;

import com.yahoo.elide.core.security.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;

import java.security.Principal;
import java.util.Optional;

public class ElideUser extends User {
  protected FafUserDetails fafUserDetails;

  public ElideUser(Principal principal) {
    super(principal);
    if (principal instanceof Authentication) {
      this.fafUserDetails = (FafUserDetails) ((Authentication) principal).getPrincipal();
    }
  }

  @Override
  public String getName() {
    return getFafUserDetailsOrAnonymous().map(details -> details.getUsername()).orElse("");
  }

  @Override
  public boolean isInRole(String role) {
    return getFafUserDetailsOrAnonymous().map(details -> details.hasPermission(role)).orElse(false);
  }

  public Optional<FafUserDetails> getFafUserDetailsOrAnonymous() {
    return Optional.ofNullable(fafUserDetails);
  }

  public FafUserDetails getFafUserDetails() {
    if (fafUserDetails != null) {
      return fafUserDetails;
    } else {
      throw new AccessDeniedException("Anonymous access");
    }
  }
}

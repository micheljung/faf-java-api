package com.faforever.integration;

import com.faforever.api.ban.BanRepository;
import com.faforever.api.clan.ClanMembershipRepository;
import com.faforever.api.clan.ClanRepository;
import com.faforever.api.client.OAuthClientRepository;
import com.faforever.api.player.PlayerRepository;
import com.faforever.api.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@Component
@Getter
public class TestDatabase {
  private ClanRepository clanRepository;
  private UserRepository userRepository;
  private ClanMembershipRepository clanMembershipRepository;
  private PlayerRepository playerRepository;
  private OAuthClientRepository oAuthClientRepository;
  private ObjectMapper objectMapper;
  private BanRepository banRepository;

  @Inject
  public void init(ClanRepository clanRepository,
                   UserRepository userRepository,
                   PlayerRepository playerRepository,
                   OAuthClientRepository oAuthClientRepository,
                   ClanMembershipRepository clanMembershipRepository,
                   BanRepository banRepository,
                   ObjectMapper objectMapper) {
    this.clanRepository = clanRepository;
    this.userRepository = userRepository;
    this.playerRepository = playerRepository;
    this.oAuthClientRepository = oAuthClientRepository;
    this.clanMembershipRepository = clanMembershipRepository;
    this.banRepository = banRepository;
    this.objectMapper = objectMapper;
  }

  public void assertEmptyDatabase() {
    assertEquals(0, banRepository.count());
    assertEquals(0, clanRepository.count());
    assertEquals(0, userRepository.count());
    assertEquals(0, playerRepository.count());
    assertEquals(0, oAuthClientRepository.count());
    assertEquals(0, clanMembershipRepository.count());
  }

  public void tearDown() {
    banRepository.deleteAll();
    clanMembershipRepository.deleteAll();
    clanRepository.deleteAll();
    userRepository.deleteAll();
    oAuthClientRepository.deleteAll();
    assertEmptyDatabase();
  }
}

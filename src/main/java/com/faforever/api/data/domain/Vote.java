package com.faforever.api.data.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.faforever.api.data.checks.IsEntityOwnerFilter;
import com.faforever.api.data.checks.IsEntityOwnerFilter.OwnerAttribute;
import com.faforever.api.data.checks.Prefab;
import com.yahoo.elide.annotation.Include;
import com.yahoo.elide.annotation.ReadPermission;
import com.yahoo.elide.annotation.UpdatePermission;

import lombok.EqualsAndHashCode;
import lombok.Setter;

@Entity
@Table(name = "vote")
@Include(type = Vote.TYPE_NAME)
@ReadPermission(expression = IsEntityOwnerFilter.EXPRESSION)
@UpdatePermission(expression = Prefab.NONE)
@EqualsAndHashCode(of = {"player", "votingSubject"}, callSuper = false)
@Setter
public class Vote extends AbstractEntity implements OwnableEntity {
  public static final String TYPE_NAME = "vote";

  @OwnerAttribute
  private Player player;
  private VotingSubject votingSubject;
  private Set<VotingAnswer> votingAnswers;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "player_id")
  public Player getPlayer() {
    return player;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "voting_subject_id")
  public VotingSubject getVotingSubject() {
    return votingSubject;
  }

  @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL, orphanRemoval = true)
  public Set<VotingAnswer> getVotingAnswers() {
    return votingAnswers;
  }

  @Transient
  @Override
  public Login getEntityOwner() {
    return getPlayer();
  }
}

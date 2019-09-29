package com.faforever.api.data.domain;

import com.faforever.api.data.checks.IsEntityOwner;
import com.yahoo.elide.annotation.CreatePermission;
import com.yahoo.elide.annotation.DeletePermission;
import com.yahoo.elide.annotation.Include;
import com.yahoo.elide.annotation.UpdatePermission;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Include(rootLevel = true, type = "gameReview")
@Entity
@Table(name = "game_review")
@CreatePermission(expression = "Prefab.Role.All")
@DeletePermission(expression = IsEntityOwner.EXPRESSION)
public class GameReview extends Review {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "game_id")
  @UpdatePermission(expression = "Prefab.Role.All and Prefab.Common.UpdateOnCreate")
  private Game game;
}

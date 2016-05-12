package de.almostintelligent.kicker.repository;

import de.almostintelligent.kicker.model.TeamInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamInvitationRepository extends JpaRepository<TeamInvitation, String> {
}

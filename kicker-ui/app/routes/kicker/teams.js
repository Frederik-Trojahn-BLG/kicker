import Ember from 'ember';

export default Ember.Route.extend({
    beforeModel() {
        return this.store.findAll('team-invitations');
    },
    model() {
        return this.transitionTo('kicker.teams.overview');
    }
});


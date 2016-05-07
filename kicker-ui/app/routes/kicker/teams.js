import Ember from 'ember';

export default Ember.Route.extend({
    model() {
        return this.store.findAll('team-invitation');
    },
    afterModel() {
        return this.transitionTo('kicker.teams.overview');
    }
});


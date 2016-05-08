import Ember from 'ember';

export default Ember.Route.extend({
    model() {
        this.store.unloadAll('team-invitation');
        return this.store.findAll('team-invitation');
    },
    afterModel() {
        return this.transitionTo('kicker.teams.overview');
    }
});


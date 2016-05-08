import Ember from 'ember';

export default Ember.Route.extend({
    beforeModel() {
        this.store.unloadAll('league');
        this.store.unloadAll('current-user');
    },
    model() {
        return Ember.RSVP.hash({
            leagues: this.store.findAll('league'),
            currentUser: this.store.findAll('current-user')
        });
    }

});

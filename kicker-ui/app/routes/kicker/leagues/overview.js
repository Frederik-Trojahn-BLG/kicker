import Ember from 'ember';

export default Ember.Route.extend({

    model() {
        return Ember.RSVP.hash({
            leagues: this.store.findAll('league'),
            currentUser: this.store.findAll('current-user')
        });
    }

});

import Ember from 'ember';

export default Ember.Route.extend({
    model() {
        return this.transitionTo('kicker.leagues.overview');
    }
});

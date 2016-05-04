import Ember from 'ember';
import AuthenticatedRouteMixin from 'ember-simple-auth/mixins/authenticated-route-mixin';

export default Ember.Route.extend(AuthenticatedRouteMixin, {
    beforeModel() {
        return this.store.findAll('current-user');
    },

    model() {
        return this.transitionTo('kicker.dashboard');
    }
});

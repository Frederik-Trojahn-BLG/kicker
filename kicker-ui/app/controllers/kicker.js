import Ember from 'ember';

export default Ember.Controller.extend({
    session: Ember.inject.service('session'),

    actions: {
        logout() {
            if (this.get('session').get('isAuthenticated')) {
                this.get('session').invalidate();
            }
        }
    }
});

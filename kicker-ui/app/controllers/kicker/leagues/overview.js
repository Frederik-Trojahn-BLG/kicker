import Ember from 'ember';

export default Ember.Controller.extend({

    myLeagues: Ember.computed.filter('model.leagues', function(league, index, array) {
        return this.get('model.currentUser.id') !== league.get('account.id');
    })

});

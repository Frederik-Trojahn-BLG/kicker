import Ember from 'ember';

export default Ember.Controller.extend({

    actions: {
        createLeague() {
            var me = this;
            let { leagueName } = this.getProperties('leagueName');
            var league = this.get('store').createRecord('league', {
                name: leagueName
            });

            league.save().then(function() {
                me.transitionToRoute('kicker.leagues.overview');
            });
        }
    }

});

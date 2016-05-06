import Ember from 'ember';

export default Ember.Controller.extend({

    actions: {
        createTeam() {
            var me = this;
            let { teamName } = this.getProperties('teamName');
            var team = this.get('store').createRecord('team', {
                name: teamName
            });

            team.save().then(function() {
                me.transitionToRoute('kicker.teams.overview');
            });
        }
    }

});

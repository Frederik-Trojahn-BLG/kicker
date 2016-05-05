import Ember from 'ember';

export default Ember.Controller.extend({

    actions: {
        createTeam() {
            let { teamName } = this.getProperties('teamName');
            var team = this.get('store').createRecord('team', {
                name: teamName
            });

            team.save().then(function() {
                this.transitionToRoute('kicker.team.overview');
            });
        }
    }

});

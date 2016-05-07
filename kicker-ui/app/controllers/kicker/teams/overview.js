import Ember from 'ember';

export default Ember.Controller.extend({
    teamSorting: ['name:asc'],
    teams: Ember.computed.sort('model', 'teamSorting'),
});

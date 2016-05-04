import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType
});

Router.map(function() {
  this.route('login');
  this.route('kicker', function() {
    this.route('dashboard');
    this.route('score-boards');
    this.route('teams');
    this.route('leagues');
  });
});

export default Router;

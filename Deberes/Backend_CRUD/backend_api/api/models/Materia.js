/**
 * Expansion.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

    attributes: {
        nombre: {
            type: 'string',
            required: true,
            unique: true
        },
        id: {
            type: 'string',
            required: true,
            unique: true
        },
        releaseDate: {
            type: 'number',
            isInteger: true,
        },
        precio: {
            type: 'number'
        },
        activa: {
            type: 'boolean',
            required: true
        },
        estudiantes: { //one to many
            collection: 'estudiante', //referencia al modelo
            via: 'materias' //nombre foreign key
        }
    },

};
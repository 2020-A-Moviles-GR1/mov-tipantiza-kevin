/**
 * Carta.js
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
        level: {
            type: 'number',
            isInteger: true,
            isIn: [0, 1, 2, 3, 4, 5, 6, 7, 8]
        },
        precio: {
            type: 'number'
        },
        graduado: {
            type: 'boolean',
            required: true
        },
        materias: {
            collection: 'materia',
            via: 'estudiantes'
        }
    },

};
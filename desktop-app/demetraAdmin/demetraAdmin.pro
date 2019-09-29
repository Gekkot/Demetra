#-------------------------------------------------
#
# Project created by QtCreator 2019-09-28T11:26:02
#
#-------------------------------------------------

QT       += core gui
QT += network

CONFIG += c++11

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = demetraAdmin
TEMPLATE = app


SOURCES += main.cpp\
        mainwindow.cpp \
    category/category.cpp \
    category/categoryediter.cpp \
    add/categorycreator.cpp \
    positions/positions.cpp \
    positions/positioneditor.cpp \
    add/positioncreator.cpp \
    requester.cpp

HEADERS  += mainwindow.h \
    category/category.h \
    category/categoryediter.h \
    add/categorycreator.h \
    positions/positions.h \
    positions/positioneditor.h \
    add/positioncreator.h \
    requester.h

FORMS    += mainwindow.ui \
    add/positioncreator.ui

RESOURCES += \
    res.qrc

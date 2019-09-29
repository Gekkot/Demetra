#ifndef POSITIONEDITOR_H
#define POSITIONEDITOR_H

#include <QObject>
#include <QWidget>
#include <QPushButton>
#include <QDebug>
#include <QLineEdit>
#include <QLabel>

class positionEditor : public QWidget
{
    Q_OBJECT
public:
    QPushButton* saveButton;
    QLineEdit* namePositionLineEdit;
    QLineEdit* costPositionLineEdit;
    explicit positionEditor(QWidget *parent = 0);

signals:
    void changePosition(QString name,QString cost);

public slots:
    void saveButtonClicked();
};

#endif // POSITIONEDITOR_H

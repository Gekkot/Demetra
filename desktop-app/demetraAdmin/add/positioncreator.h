#ifndef POSITIONCREATOR_H
#define POSITIONCREATOR_H

#include <QWidget>
#include <QDialog>
#include <QLabel>
#include <QLineEdit>
#include <QPushButton>

namespace Ui {
class positionCreator;
}

class positionCreator : public QDialog
{
    Q_OBJECT

public:
    explicit positionCreator(QWidget *parent = 0);
    ~positionCreator();

signals:
    void savePosition(QString name,QString cost);

private slots:
    void on_saveButton_clicked();

private:
    Ui::positionCreator *ui;
};

#endif // POSITIONCREATOR_H
